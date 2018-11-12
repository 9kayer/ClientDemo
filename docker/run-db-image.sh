docker run -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=saWasHere??01' -P -p 1433:1433 --name client-demo-db -d client-demo-db-image
