(ns experimentrpc.scripts.ls
  (:require [clojure.tools.cli :refer [parse-opts]]
            [me.raynes.fs :as fs]))


(def cli-options
  [[nil "--help"]])


(defn main [env args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (if (:help options)
      summary
      (:out (fs/exec "ls" "-alh" :env env)))))


(defn -main [& args]
  (main {} args))
