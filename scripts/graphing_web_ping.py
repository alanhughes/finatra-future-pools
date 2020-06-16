import concurrent.futures
import logging
import matplotlib.pyplot as plt
import requests
import time

# import time
PINGS = 2
URL = "http://localhost:8080"

if __name__ == "__main__":
    format = "%(asctime)s: %(message)s"
    logging.basicConfig(format=format, level=logging.INFO,
                        datefmt="%H:%M:%S")
    now = time.time()
    future = now + 20
    req_times = []
    while time.time() < future:
        resp = requests.get(URL)
        print(resp.content)
        req_times.append(resp.elapsed.microseconds)
        print(resp.elapsed.microseconds)
        time.sleep(0.1)
    print(sum(req_times) / len(req_times))
    plt.ylim(top=50000)
    plt.plot(req_times)

    plt.show()
