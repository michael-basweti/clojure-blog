(ns clojure-blog.db
  (
    :require [monger.core :as mg]
             [monger.collection :as mc]
    )
  )

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