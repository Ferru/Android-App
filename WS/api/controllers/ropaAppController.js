'use strict';
var mongoose = require('mongoose'),
    Buyer = mongoose.model('Buyer'),
    User = mongoose.model('User');

exports.list_all_users= function(req, res){
    Buyer.find({}, function(err, buyer){
	if(err)
	    res.send(err);
	res.json(buyer);
    });
};

exports.create_a_user = function(req, res){
    var new_User = new User(req.body);
    var new_Buyer = new Buyer(req.body);
    new_User.save(function(err, user){
	if(err)
	    res.send(err);
	res.write(JSON.stringify(user));
    });
    new_Buyer.user = new_User._id;
    new_Buyer.save(function(err, buyer){
	if(err)
	    res.send(err);
	res.write(JSON.stringify(buyer));
    });
};
