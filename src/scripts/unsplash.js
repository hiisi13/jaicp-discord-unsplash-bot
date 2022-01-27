var UnsplashAPI = {
    search: function (query) {
        var pictures = $http.get("https://api.unsplash.com/search/photos?page=1&per_page=3&query=" + query, {
            dataType: "json",
            query: {            // параметры запроса, для подстановки в URL
                query: query
            },
            headers: {
                "Authorization": "Client-ID // replace with your Unsplash API access key"
            },
            timeout: 10000
        });
        return pictures.data;
    }
};
