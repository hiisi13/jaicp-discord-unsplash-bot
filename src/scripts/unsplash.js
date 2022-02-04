var API_KEY = "// replace with your Unsplash API access key";

var UnsplashAPI = {
  search: function (query) {
    var pictures = $http.get(
      "https://api.unsplash.com/search/photos?page=1&per_page=3&query=" + query,
      {
        dataType: "json",
        query: {
          query: query,
        },
        headers: {
          Authorization: "Client-ID " + API_KEY,
        },
        timeout: 10000,
      }
    );
    return pictures.data;
  },

  random: function () {
    var picture = $http.get("https://api.unsplash.com/photos/random", {
      dataType: "json",
      headers: {
        Authorization: "Client-ID " + API_KEY,
      },
      timeout: 10000,
    });
    return picture.data;
  },
};
