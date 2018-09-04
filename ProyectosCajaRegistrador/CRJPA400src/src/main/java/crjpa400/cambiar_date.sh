#!/bin/bash
##################################################
# Script creado por Magroberth Alvarado
# para cambiar los Date por Calendar 
# Copialo en tu workspace y ejecutalo
##################################################
echo -n "Cambiando Date por Calendar  "
find . -iname "*.java" | xargs perl -pi -e 's/Date getUltimasincronizacion/Calendar getUltimasincronizacion/g'
find . -iname "*.java" | xargs perl -pi -e 's/Date ultimasincronizacion/Calendar ultimasincronizacion/g'

echo "... Listo!"
