window.axe = {
  run: function (context, options, callback) {
    setTimeout(function () {
      callback()
    }, 1000 * 60 * 5)
  }
}