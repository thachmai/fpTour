(ns github-api.core-test
  (:require [clojure.test :refer :all]
            [github-api.core :refer :all]))

(deftest get-github-repos-test
  (testing "Testing retrieval from github api"
    (let [default-ret (get-github-repos)
          javascript-ret (get-github-repos "javascript")]
      (is (vector? default-ret))
      (is (< 0 (count default-ret)))
      (is (vector? javascript-ret))
      (is (< 0 (count javascript-ret))))))

(deftest generate-html-test
  (testing "Testing generation of html"
    (let [test-data [{:html_url "http://test.com" :name "Test" :stargazers_count 1 :description ""}]
          generated (generate-html test-data)]
      (is (string? generated))
      (is (= "table" (re-find #"table" generated))))))
