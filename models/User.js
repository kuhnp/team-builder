var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var UserSchema   = new Schema({
     email: String,
     password: String,
     token: String,
     street: String,
     postcode: String,
     city: String,
     name: String,
     fName: String
});

module.exports = mongoose.model('User', UserSchema);
