/**
 * @gg87
 *
 * Contain some constants
 *
 */
var CONFIG = (function() {
     var mediaType = {
         1: 'image',
         2: 'video',
         3: 'sound',
     };


     return {
        get: function(name) { return mediaType[name]; }
    };
})();
