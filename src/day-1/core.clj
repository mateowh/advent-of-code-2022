(defn cal-string->cals
  [group]
  (let [split (clojure.string/split group #"\n")]
    (map #(Integer/parseInt %) split)))

(defn find-max-cals []
  (let [input (slurp "input.txt")
        food-per-elf (clojure.string/split input #"\n\n")
        total-cals-per-elf (map #(->> % cal-string->cals (apply +)) food-per-elf)]
    (apply max total-cals-per-elf)))