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
    let usuarioS = req.body.usuario;
    /*
      Validate that the users does not existi
     */
    User.find({usuario:usuarioS}, function(err, us1){
	/* The search must return an empty users list, validate errors*/
	if(us1.length === 0)
	{
	    var new_User = new User(req.body);
	    new_User.save().
		then(user =>{
		    var new_Buyer = new Buyer(req.body);
		    new_Buyer.user = user._id;		  
		    new_Buyer.save()
			.then(buyer =>{
			    res.status(200).json(buyer);
			})
			.catch(err =>{
			    res.status(400).send(err);
			});

		})
		.catch(err =>{
		    res.status(400).send(err);
		});
	}
	else
	{
	    res.status(200).send("Usuario ya existe");
	}
    });
};
/* Function for log into the system */
exports.log_user = function(req, res){
    let userLoc = req.params.user;
    let password = req.params.password;
    User.findOne({usuario:userLoc}, function(err, user){
	if(err)
	{
	    res.status(400).send(err);
	}
	else if(user == null || typeof user === "undefined" || user.length === 0)
	{
	    res.status(200).send("Usuario " + userLoc + " no existe");
	}
	else if(password !== user.password)
	{
	    console.log(password);
	    console.log(user.password);
	    res.status(200).send("Contraseña incorrecta");
	}
	else
	{
	    //Search buyer
	    Buyer.findOne({user:user._id}, function(err, buyer){
		if(err)
		{
		    res.status(400).send(err);
		}
		else
		{
		    res.status(200).json(buyer);
		}
	    });
	}
    });
};
