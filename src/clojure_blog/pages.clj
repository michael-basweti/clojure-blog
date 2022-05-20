(ns clojure-blog.pages
  (:require [clojure-blog.db :as db]
            [hiccup.page :refer [html5]])
  )

(defn base-page [& body]
  (html5
    [:head (:title "CLJBlog")]
    [:body
     [:h1 "CLJBLog"]
     body])
  )

(defn index [articles]
  ;(->> articles
  ;     (map #(str "<h2>" (:title %) "</h2>"))
  ;     ;(map #(println (:title %)))
  ;     ;(map #(str "<h2>" (:body %) "</h2>"))
  ;     (apply str "<h1>CljBlog</h1>")
  ;
  ;     )
(base-page
         (for [a articles]
           ;[:h2 [:a {:href (str "/article/" (:_id a))}(:title a)]]
           [:h2 [:a {:href (str "/article/" (:_id a))}(:title a)]]

           )))

(defn article [a]
  (base-page
    [:small (:created a)]
    [:h1 (:title a)]
    [:p (:body a)]))
