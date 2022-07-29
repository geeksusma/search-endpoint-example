# search-endpoint-example

## Why pagination is important?

A lot of modern applications are loading data at the browser, and then they can filter/sorting the data there at
component level. This is ok if you don't expect a heavy amount of data.

However, if you expect a heavy amount of data, then is better to filter at backend side, providing a pagination
mechanism. The reason is simple, reading a big resultset from a database can lead to a deadlock, and the system could
fail.

## Search endpoint in a Restful API

Even there is no standard for the semantics, and you can name your search endpoint as you want, I often encourage
the [google way](https://cloud.google.com/blog/products/api-management/restful-api-design-tips-search), this is because
is close to search engines, and also is easy to model and understand.

For example:

https://localhost:8080/v1/contacts/search?name=Joao&phone=3&page=1&offset=20

As you can see, all the query parameters are placed at URL level. This is important to respect one rule of a Get Restful
Endpoint. Get calls must be able to be cached, so that means we can't use any payload at body, so then we have to
discard POST

Even the URL could be longer, is not a problem, have a look at the google search engine and check how long a URL could
be ;)

## Some basic rules for searching with pagination at backend side

For a Search endpoint, is so weird to deal with status http code different than 200 (of course I assume you're already
logged with enough permissions into the system so no 401/403)
In case of receiving a filtering which lead to not found resources, then is enough to return 200 + empty Not sending a
query parameter should be accepted, not returning a 400

In addition, from the point of view of the client who is using our API. Is important to receive, not only the data who
matched with the search done at that page, it is important also to receive how many elements were hit by the Search.
This is so important since probably at frontend side we want to dynamically calculate how many pages we can navigate
based in those results

### Failover policies

As I mentioned, is important to don't lead to 400 if there is any missing query parameter. In case of pagination, we
need to define policies like:

* Return empty data if:
    * Not found pages (a negative page number or if we only can serve 20 but it is requested the 21)
    * Not accepted offset (a negative offset or an offset that could kill the database)

## This example:

To run this application is just enough if you checkout the codebase and then, from the terminal:

```
mvn clean package
java -jar target/search-endpoint-example-1.0.0-SNAPSHOT.jar
```

Or just leave your favorite IDE do the job for you

Then just start playing sending some filters to the contacts, use the next url as examples:

* http://localhost:8080/v1/contacts/search (basic search)
* http://localhost:8080/v1/contacts/search?name=Joao&page=3&offset=20 (only by name)
* http://localhost:8080/v1/contacts/search?phone=34-&page=40&offset=3 (only by phone)
* http://localhost:8080/v1/contacts/search?phone=34-&name=Juan&page=4&offset=21 (name and phone)
* http://localhost:8080/v1/contacts/search?phone=34-&name=Juan&page=4000&offset=21 (not found page)
* http://localhost:8080/v1/contacts/search?phone=34-&name=Juan&page=4&offset=2100 (invalid offset)