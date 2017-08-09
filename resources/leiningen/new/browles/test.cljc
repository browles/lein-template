(ns {{namespace}}-cljc-test
  (:require #?(:clj  [clojure.test :refer :all]
               :cljs [cljs.test :refer-macros [deftest is testing]])
            [{{namespace}}.utils :as utils]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
