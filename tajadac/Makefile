SHELL     =  bash

CC        = gcc
CFLAGS    = -ggdb -Wall -Wextra $(defines:%=-D%)

CXX       = g++
CXXFLAGS  = -ggdb -Wall -Wextra $(defines:%=-D%) -std=gnu++0x

YACC      = bison
YACCFLAGS = --warnings=all --report=all -g -x

CPP       = gcc -E
CPPFLAGS  = -P

LDLIBS    = re2


defines   = nullptr=NULL _GNU_SOURCE TAJADA_DEBUG=1
cpp-magic = $(CPP) $(CPPFLAGS) -DMACRO_MAGIC -



build-exec = $(CXX) $(CXXFLAGS) -o $@ $^ $(LDLIBS:%=-l%)



%.o: %.cc
	$(CXX) $(CXXFLAGS) -c ./$<



.PHONY: all clean show

all: tajadac

clean:
	rm -f ./*.o ./*.gch ./*.out ./*.yy ./*.gen.* tajadac location.hh parser.dot parser.output parser.tab.cc parser.tab.hh parser.xml position.hh stack.hh



tajadac: ast.o lex.o main.o parser.tab.o scope.o type.o ; $(build-exec)


#          .o:           .cc tokens.hh scope.hh ast.hh type.hh lex.hh debug.hh parser.tab.hh stack.hh location.hh position.hh
        ast.o:        ast.cc                    ast.hh type.hh        debug.hh
        lex.o:        lex.cc tokens.hh scope.hh ast.hh type.hh lex.hh debug.hh parser.tab.hh stack.hh location.hh position.hh
       main.o:       main.cc tokens.hh scope.hh ast.hh type.hh lex.hh          parser.tab.hh stack.hh location.hh position.hh
 parser.tab.o: parser.tab.cc           scope.hh ast.hh type.hh                 parser.tab.hh stack.hh location.hh position.hh
      scope.o:      scope.cc           scope.hh ast.hh type.hh                                        location.hh
       type.o:       type.cc                           type.hh

#ast.gen.cc ast.gen.hh: make_ast_classes data/ast/classes data/ast/members
#	./make_ast_classes data/ast/classes data/ast/members ast.gen.cc ast.gen.hh

location.hh parser.dot parser.output parser.tab.cc parser.tab.hh parser.xml position.hh stack.hh token_data.out token_data_line.out: parser.y tokens.hh Makefile
	@printf '%s\n'                                                                    \
                '#include "tokens.hh"'                                                    \
                '#define X(tag, description, r, type) type|%token@<tag>@tag@description!' \
                'TAJADA_TOKEN_DATA(X)'                                                    \
                | $(cpp-magic)                                                            \
                | sed 's/ \?! \?/\n/g'                                                    \
                | sed                                                                     \
                        -e 's/^void|\(%token@\)<[^>]*>\(@[^ ]*@\)u8\(.*\)$$/\1 \2\3/'     \
                        -e 's/^[^|]*|\(%token@<[^>]*>@[^ ]*@\)u8\(.*\)$$/\1\2/'           \
                        -e 's/<INFIX\([LR]\|\)[0-9]\+>/<infix_op>/g'                      \
                        -e 's/" TAJADA_PASTE \(u8\)\?"//g'                                \
                | column -t -s @                                                          \
                > token_data.out
	@printf '%s\n'     \
                ''         \
                '%union {' \
                >> token_data.out
	@printf '%s\n'                                       \
                '#include "tokens.hh"'                       \
                '#define X(tag, d, r, type) type|type@tag;!' \
                'TAJADA_TOKEN_DATA(X)'                       \
                | $(cpp-magic)                               \
                | sed 's/ \?! \?/\n/g'                       \
                | sed                                        \
                        -e '/^void|/d'                       \
                        -e 's/^[^|]*|/        /'             \
                        -e '/^$$/d'                          \
                | column -t -s @                             \
                >> token_data.out
	@printf '%s\n' '}' >> token_data.out
	@tr -d '\n' < token_data.out > token_data_line.out
	@printf '%s\n' '' >> token_data_line.out
	@printf '%s\n'                                            \
                '1s@.*@@'                                         \
                '1s@$$@/* THIS FILE WAS AUTOMATICALLY GENERATED@' \
                '1s@$$@ BY THE BUILD SCRIPTS USING THE CONTENTS@' \
                '1s@$$@ OF “tokens.hh” AND “parser.y”; EDIT@'     \
                '1s@$$@ THOSE FILES INSTEAD OR YOUR CHANGES@'     \
                '1s@$$@ WILL BE LOST ONCE IT’S REGENERATED */@'   \
                '/^%token_data$$/r token_data_line.out'           \
                '/^%token_data$$/d'                               \
                'w $<y'                                           \
                'q'                                               \
                | ed -s ./$<
	$(YACC) $(YACCFLAGS) ./$<y
