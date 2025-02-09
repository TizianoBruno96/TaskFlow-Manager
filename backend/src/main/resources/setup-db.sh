#!/bin/bash

# Nome del container e del database
DB_NAME="bookhaven"
DB_USER="root"
DB_PASSWORD="1234"
DB_PORT="5432"

# Crea il file secrets.yaml
echo "Creazione del file secrets.yaml..."
cat <<EOF > secrets.yaml
quarkus:
  datasource:
    username: $DB_USER
    password: $DB_PASSWORD
EOF

# Crea il container Docker con PostgreSQL
echo "Avvio del container Docker con PostgreSQL..."
docker run --name bookhaven-db \
  -e POSTGRES_DB=$DB_NAME \
  -e POSTGRES_USER=$DB_USER \
  -e POSTGRES_PASSWORD=$DB_PASSWORD \
  -p $DB_PORT:5432 \
  -d postgres:latest

# Verifica che il container sia attivo
echo "Verifica che il container sia attivo..."
docker ps --filter "name=bookhaven-db"

echo "Setup completato!"