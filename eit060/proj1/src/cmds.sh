##1##
openssl genrsa -out privkey.pem 2048 -alias key1
openssl req -new -x509 -key privkey.pem -out publickey.cer

##2##
keytool -keystore clienttruststore -importcert -file publickey.cer -alias rootCA

##3##
keytool -genkeypair -keystore clientkeystore

##4##
keytool -keystore clientkeystore -certreq -file publickey.csr

##5##
openssl x509 -req -in publickey.csr -CA publickey.cer -CAkey privkey.pem -CAcreateserial -out newcert.cer

##6##
keytool -keystore clientkeystore -importcert -file publickey.cer -alias rootCA
keytool -keystore clientkeystore -importcert -file newcert.cer


##9##
keytool -genkeypair -keystore serverkeystore
keytool -keystore serverkeystore -certreq -file server.csr
openssl x509 -req -in server.csr -CA publickey.cer -CAkey privkey.pem -CAcreateserial -out servercert.cer
keytool -keystore serverkeystore -importcert -file publickey.cer -alias rootCA
keytool -keystore serverkeystore -importcert -file servercert.cer

##10##
keytool -keystore servertruststore -importcert -file publickey.cer -alias servercert
