var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var BandSchema   = new Schema({
     id: String,
     name:String,
     music: String,
     city: String,
});

module.exports = mongoose.model('Band', UserSchema);
