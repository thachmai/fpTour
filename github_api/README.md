# github_api

This example requires [Leiningen](http://leiningen.org) to run.
If you have JDK 1.7 or 1.8 already installed, getting started with Leiningen should take less than 5 minutes.

Once you have leiningen, simply run `lein deps` in this directory to download all 3rd party dependencies. 

I recommend running `lein repl` in this directory to start exploring the code.

Some examples that you can run:

```
; to reload the changes in src/github_api/core.clj
(load "core")

; to see how the html is generated
(generate-html [{:name "name" :html_url "HTML" :description "Description" :stargazers_count 2}])

; to start the web server on port 8080
(-main)

; after the web server is started, you can stop/start the server with
(.stop server)
(.start server)
```
