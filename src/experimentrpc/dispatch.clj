(ns experimentrpc.dispatch
  (:require [clojure.string :as str]
            [me.raynes.fs :as fs]
            [taoensso.timbre :as log]
            [experimentrpc.scripts.ls :as scripts.ls]))


(defn parse-env [s]
  (into {}
        (for [line (str/split-lines s)
              :let [[_ var val] (re-find #"([^=]+)=(.*)" line)]]
          [var val])))


(defn dispatch [name env params]
  (log/info "dispatch" name "with" params)
  (let [env (parse-env env)]
    (fs/with-cwd (env "PWD")
      (case name
        "ls" (scripts.ls/main env params)))))

