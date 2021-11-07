#!/bin/bash
#pattern='^(\bMetodytki DeVops)'
pattern='^(\bRBMK)-[0-9]+'
msg="NIE PODALES NAZWY PRZEDMIOTU"

if ! grep -iqE "$pattern" "$1"; then
        echo "$msg"
        exit 1;
fi
echo "Jest ok"


