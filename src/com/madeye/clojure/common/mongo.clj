(ns com.madeye.clojure.common.mongo
	"Collected mongo utilities"
  (:require
    [monger.core :as mg]
    [monger.collection :as mc]
    [com.madeye.clojure.common.config :as cfg]
    [clojure.walk]
  )
  (:import [org.bson.types ObjectId])
)

(defn reload [] (use :reload-all 'com.madeye.clojure.common.mongo))

; Multi-methods are much slower than protocols so ...
; (defmulti get-id
;   "Multimethod to allow us to treat stringified object ids the same as object ids"
;   (fn [id] (class id))
; )
; 
; (defmethod get-id java.lang.String [id] (ObjectId. id))
; (defmethod get-id org.bson.types.ObjectId [id] id)

(defprotocol GetId
  (get-id [id])
  (get-str [id])
)

(extend-protocol GetId java.lang.String
  (get-id [id] (ObjectId. id))
  (get-str [id] id)
)
(extend-protocol GetId org.bson.types.ObjectId 
  (get-id [id] id)
  (get-str [id] (str id))
)
(extend-protocol GetId Object
  (get-id [obj] obj)
  (get-str [obj] obj)
)

(defn objectid?
  [id]
  (= (class id) org.bson.types.ObjectId)
)

(defn object-id-to-str
  [m]
  (clojure.walk/postwalk #(if (objectid? %) (get-str %) %) m)
)

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
