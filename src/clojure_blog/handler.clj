(ns clojure-blog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure-blog.db :as db]
            [clojure-blog.pages :as pg]))

(defroutes app-routes
  (GET "/" [] (pg/index (db/list-articles)))
  (GET "/article/:article-id" [article-id] (pg/article (db/get-article-by-id article-id)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
