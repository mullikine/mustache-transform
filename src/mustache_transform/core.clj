(ns mustache-transform.core
  (:gen-class)
  (:require [clj-yaml.core :as :yaml]))

(defn -main
  "I don't do a whole lot ... yet."
  ([template_fp data_fp transform_fp & args] (println template_fp data_fp transform_fp)))
