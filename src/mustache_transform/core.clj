(ns mustache-transform.core
  (:gen-class)
  (:require [clj-yaml.core :as yaml]
            [clojure.pprint :as pp]))

(use '[clojure.java.shell :only [sh]])

(defn -main
  "I don't do a whole lot ... yet."
  ([template-fp data-fp transform-fp & args]
   (let [template (slurp template-fp)
         data (yaml/parse-string (slurp data-fp))
         transform (yaml/parse-string (slurp transform-fp))]
     (println template)
     ;; [[tcq:clojure select multiple keys from map]]
     ;; (select-keys {:a 1 :b 2 :c 3} [:a :b])
     (pp/pprint data)
     (pp/pprint transform)

     ;; Take the union of both map keys vectors to find the subset
     ;; Get the values of both maps according to that vector
     ;; Map / zip? over the keys vector and both values vectors

     ;; I think I want interleave
     ;; https://clojuredocs.org/clojure.core/interleave

     (doseq [keyval data]
       (let [tr (get transform (first keyval))]
         (if tr
           (assoc! data tr (sh tr :in (get data tr))))))

     (println template-fp data-fp transform-fp))))
