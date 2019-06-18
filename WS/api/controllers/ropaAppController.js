'use strict';
var mongoose = require('mongoose');
Buyer = mongoose.model('Buyer');

exports.list_all_users= function(req, res){
    Buyer.find({}, function(err, buyer){
	if(err)
	    res.send(err);
	res.json(buyer);
    });
};

exports.create_a_user = function(req, res){
    var new_Buyer = new Buyer(req.body);
    new_Buyer.save(function(err, buyer){
	if(err)
	    res.send(err);
	res.json(buyer);
    });
});
