(defn cal-string->cals
  [group]
  (let [split (clojure.string/split group #"\n")]
    (map #(Integer/parseInt %) split)))

(def input-data
  (let [raw-input (slurp "src/day-1/input.txt")
        food-per-elf (clojure.string/split raw-input #"\n\n")
        total-cals-per-elf (map #(->> % cal-string->cals (apply +)) food-per-elf)]
    total-cals-per-elf))

;; part 1
(defn find-max-cals []
  (apply max input-data))

;; part 2
(defn sum-top-3-elf-cals []
  (->> input-data (sort >) (take 3) (apply +)))