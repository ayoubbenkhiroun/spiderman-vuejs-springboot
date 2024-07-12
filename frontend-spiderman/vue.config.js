/*module.exports = {
  css: {
    loaderOptions: {
      sass: {
        // Si vous utilisez Dart Sass (recommandé)
        implementation: require('sass'),
        sassOptions: {
          indentedSyntax: true, // optionnel
        },
      },
    },
  },
};*/

module.exports = {
  publicPath: process.env.NODE_ENV === 'production' ? '/' : '/'
}

