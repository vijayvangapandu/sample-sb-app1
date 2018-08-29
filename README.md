# Sample webapplication

This application requires MongoDB.

### For local development
1. Intall mongodb locally if it is not done already
2. Start mongod instance (make sure it is not running already: ps -ef|grep mongo)
3. Make sure that you are pointing to correct mongo instance by looking at application.properties or application-override.properties

Good Luck!

//User Resource Payload

Save User:
URL: http://localhost:7001/interview/v1/users
{"name":"vijay5", "gender":1, "email":"vija5@gmail.com","birthDateRaw":709974025000 }
{"name":"female1", "gender":2, "email":"female1@gmail.com","birthDateRaw":709974025000 }

//GEt users

// swipeLeft


