(ns clojure-blog.db
  (
    :require [monger.core :as mg]
             [monger.collection :as mc]
             )
  (:import (org.bson.types ObjectId)))

(def db-connection-uri (or (System/getenv "CLJBLOGURI")
                           "mongodb://127.0.0.1/cljblog"
                           ))

(def db (-> db-connection-uri
            mg/connect-via-uri
            :db))
(def article-coll "articles")

(defn create-article [title, body]
  (mc/insert db article-coll
             {
              :title title
              :body body
              :created (new java.util.Date)
              }
             )
  )

(defn list-articles []
  (mc/find-maps db article-coll)
  )

(defn get-article-by-id [article-id]
  (mc/find-map-by-id db article-coll (ObjectId. article-id))
  )