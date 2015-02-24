/**
 * @gg87
 *
 * Contain some constants
 *
 */
var mediaType = {
         1: 'image',
         2: 'video',
         3: 'sound',
};

var roles = {
        1: 'guitar',
        2: 'drum',
        3: 'voice'
}

var getMediaConst = function(name)
{
       return mediaType[name];
}

var getRoleConst = function(name)
{
       return roles[name];
}



module.exports.getMediaConst = getMediaConst;
module.exports.getRoleConst = getRoleConst;
