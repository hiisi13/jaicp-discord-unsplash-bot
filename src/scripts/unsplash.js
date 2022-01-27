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
        log(pictures.data);
        return pictures.data;
    },
    
    random: function () {
        var picture = $http.get("https://api.unsplash.com/photos/random", {
            dataType: "json",
            headers: {
                "Authorization": "Client-ID LLbR7h_Ce0ntXaTwR8T31Tz8hArUB2oLJgi94JSnNaM"
            },
            timeout: 10000
        });
        log(picture.data);
        return picture.data;    
    }
};
