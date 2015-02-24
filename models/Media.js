var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var MediaSchema   = new Schema({
     id: String,
     name:String,
     type: int,
     src: String,
     bandId: String
});

module.exports = mongoose.model('Media', UserSchema);
