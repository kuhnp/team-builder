var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var BandComponentSchema   = new Schema({
     idBand: String,
     username:String,
     role: int,
});

module.exports = mongoose.model('Media', UserSchema);
