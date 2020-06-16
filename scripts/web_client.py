import concurrent.futures
import logging
import requests
# import time
#
def request():
    logging.info("Hitting webserver")
    r = requests.get("http://localhost:8080", data={"foobar": "baz"})
    logging.info(r.status_code)
    logging.info(len(r.content))


if __name__ == "__main__":
    format = "%(asctime)s: %(message)s"
    logging.basicConfig(format=format, level=logging.INFO,
                        datefmt="%H:%M:%S")

    with concurrent.futures.ThreadPoolExecutor(max_workers=50) as executor:
        futures = []
        for index in range(300):
            futures.append(executor.submit(request))

            # Return the value returned by shutil.copytree(), None.
            # Raise any exceptions raised during the copy process.
        for future in futures:
            if future.exception():
                print(repr(future.exception()))
