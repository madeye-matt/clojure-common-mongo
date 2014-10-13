(ns com.madeye.clojure.common.mongo
	"Collected mongo utilities"
  (:require
    [monger.core :as mg]
    [monger.collection :as mc]
    [com.madeye.clojure.common.config :as cfg]
  )
  (:import [org.bson.types ObjectId])
)

(defmulti get-id
  "Multimethod to allow us to treat stringified object ids the same as object ids"
  (fn [id] (class id))
)

(defmethod get-id java.lang.String [id] (ObjectId. id))
(defmethod get-id org.bson.types.ObjectId [id] id)

(defn initialise
  []
  (let [uri (cfg/get-property :mongo.url)
        conn (mg/connect-via-uri uri)
        db (:db conn)]
    (cfg/set-property :mongo.connection conn)
    (cfg/set-property :mongo.db db)
    db
  )
)
