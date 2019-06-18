'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var BuyerTask = new Shema({
    nombre:
    {
	type:String,
	required:'Kindly enter the first name of the Buyer'
    },
    apellido:
    {
	type:String,
	required:'Kindry enter the last name of the Buyer'
    },
    telefono:
    {
	type:String,
	required:'Kindly enter the phone number'
    },
    usuario:
    {
	type:String,
	required:'Kindly enter the user name'
    },
    password:
    {
	type:String,
	required:'Kindly enter the password'
    }
});
module.exports = mongoose.model('Buyer', BuyerSchema);
