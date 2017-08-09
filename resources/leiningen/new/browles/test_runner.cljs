(ns {{namespace}}-cljs.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [{{namespace}}-cljs-test]))

(doo-tests '{{namespace}}-test)
