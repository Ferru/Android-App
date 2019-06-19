'use strict';
module.exports = function(app) {
    var ropaList = require('../controllers/ropaAppController');

    //Ropa App Routes
    app.route('/buyer')
	.get(ropaList.list_all_users)
	.post(ropaList.create_a_user);
/*
    app.route('/buyer/:buyerId')
	.get(ropaList.read_a_user)
	.put(ropaList.update_a_user)
	.delete(ropaList.delete_a_user);*/
};
