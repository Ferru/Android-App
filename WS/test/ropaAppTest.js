let chai = require('chai');
let chaiHttp = require('chai-http');
const expect = require('chai').expect;

chai.use(chaiHttp);
const url = 'http://localhost:3000';

describe('Insert a new Buyer/User: ', () =>{
    it('should insert a Buyer', (done) => {
	chai.request(url)
	    .post('/buyer')
	    .send({nombre:'Fabian', apellido:'Cuesta', telefono:'12356987', user:{usuario:'fcuesta', password:'test3'}})
	    .end(function(err, res){
		console.log(res.body);
		expect(res).to.have.status(200);
		done();
	    });
    });
});
