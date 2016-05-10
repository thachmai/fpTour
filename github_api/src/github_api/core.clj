(ns github-api.core)

(require 'clj-http.client)
(require 'cheshire.core)
(require 'hiccup.core)
(require 'markdown.core)
(require 'ring.adapter.jetty)

(defn generate-item [item]
  [:tr
   [:td [:a {:href (:html_url item)} (:name item)]]
   [:td (:stargazers_count item)]
   [:td (:description item)]])

(defn generate-html [items]
  (hiccup.core/html [:table 
                     [:thead
                      [:th "Name"]
                      [:th "Stars"]
                      [:th "Description"]]
                     (map generate-item items)]))

(defn get-github-repos
  ([] (get-github-repos "clojure"))
  ([lang]
   (-> (clj-http.client/get "https://api.github.com/search/repositories"
                            {:query-params 
                             {:q (str "language:" lang) :sort "stars"}})
       :body
       (cheshire.core/parse-string true)
       :items)))

(defn app [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (generate-html (get-github-repos))})

(defn -main []
  (defonce server (ring.adapter.jetty/run-jetty #'app {:port 8080 :join? false})))
