require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: scripts/unsplash.js

theme: /

    state: Start
        q!: $regex</start>
        a: Let's start.

    state: Hello
        intent!: /hello
        a: Hello hello

    state: Bye
        intent!: /bye
        a: Bye bye

    state: NoMatch
        event!: noMatch
        a: I don't understand. You said: {{$request.query}}

    state: SearchUnsplash
        intent: /search
        go!: /SearchUnsplash/RequestQuery

        state: RequestQuery
            a: What should I search for?

            state: SearchPictures
                q: *
                script:
                    var query = $request.query;
                    log("query=" + $request.query);
                    var pictures = UnsplashAPI.search(query);

                    $response.replies = $response.replies || [];
                    var content = [];
                    pictures.results.forEach(function (picture) {
                        content.push({
                            "title": picture.description,
                            "image": picture.urls.small,
                            "url": picture.links.html,
                            "btnText": "View on Unsplash"
                        });
                    });

                    var reply = {
                        "type": "carousel",
                        "text": "Unsplash search results for \"" + query + "\":",
                        "content": content
                    };
                    $response.replies.push(reply);
                go: /

    state: ScheduleRandom
        intent!: /ImageOfTheDay
        script:
            $session.reminderTime = $parseTree["_reminderTime"];
            var event = $pushgate.createEvent(
                $session.reminderTime.value,
                "scheduleEvent"
            );
            $session.reminderId = event.id;
            $temp.reminderTime = moment($session.reminderTime.value).locale("en").calendar();
        a: Very well, your random Unsplash picture will arrive at {{$temp.reminderTime}}.


    state: Remind
        event!: scheduleEvent
        script:
            var picture = UnsplashAPI.random();

            $response.replies = $response.replies || [];
            var content = [];
            content.push({
                "title": picture.description || "No description",
                "image": picture.urls.small,
                "url": picture.links.html,
                "btnText": "View on Unsplash"
            });

            var reply = {
                "type": "carousel",
                "text": "Your scheduled random picture",
                "content": content
            };
            $response.replies.push(reply);
