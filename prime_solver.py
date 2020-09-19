from typing import List


class PrimeSolver:
    primes: set = set([0, 1])
    max_n: int = 1  # we start with all primes less than max_n

    def populate_primes(self, n: int):
        candidates: set = set()
        for i in range(self.max_n + 1, n + 1):
            candidates.add(i)
        for j in range(self.max_n + 1, n):
            tmp = j
            while tmp <= n:
                tmp += j
                candidates.discard(tmp)
        for prime in candidates:
            self.primes.add(prime)

    def is_prime(self, n: int) -> bool:
        if n < self.max_n:
            return n in self.primes
        else:
            self.populate_primes(n)
            return n in self.primes

    def calculate_primes(self, n: int) -> list:
        self.populate_primes(n)
        result: list = []
        for i in range(0, n + 1):
            if i in self.primes:
                result.append(i)
        return result


def test_prime_solver():
    s = PrimeSolver()
    assert s.is_prime(0)
    assert s.is_prime(1)
    assert not s.is_prime(-1)
    assert not s.is_prime(10)
    assert s.is_prime(12323)
    assert not s.is_prime(12322)
    assert s.is_prime(197)
    print(s.calculate_primes(13))


test_prime_solver()
