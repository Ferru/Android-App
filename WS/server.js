var express = require('express'),
    app = express(),
    port = process.env.PORT || 3000,
    mongoose = require('mongoose'),
    Buyer = require('./api/models/ropaAppModel'),
    bodyParser = require('body-parser');

//mongoose instance connection url connection
mongoose.Promise = global.Promise;
//And
mongoose.connect('mongodb+srv://ferru:projectropaandroid@cluster0-wyvzq.mongodb.net/test?retryWrites=true&w=majority');
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

var routes = require('./api/routes/ropaAppRoutes'); //importing route
routes(app); //register the route
	

app.listen(port);
console.log('Ropa RESTful API server started on :' + port);
