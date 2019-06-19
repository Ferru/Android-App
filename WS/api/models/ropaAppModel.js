'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var BuyerSchema = new Schema({
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
    user:
    {
	type:mongoose.Schema.Types.ObjectId,
	ref:'User'
    }
    
});

var UserSchema = new Schema({
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
module.exports = mongoose.model('User', UserSchema);
module.exports = mongoose.model('Buyer', BuyerSchema);
