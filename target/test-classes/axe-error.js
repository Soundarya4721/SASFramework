window.axe = {
  run: function(context, options, callback) {
    var error = new Error('boom!');
    callback(error, null);
  }
};