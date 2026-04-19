import cv2
import numpy


def test_cut():
    img = cv2.imread("../test.jpg")
    print(f"px at 100x100: {img[100, 100]}")

    print(f"image shape: {img.shape}, size: {img.size}, dtype: {img.dtype}")
    rows, columns, channels = img.shape
    assert rows * columns * channels == img.size

    img_copy = img[rows // 4:(rows // 4) * 3, columns // 4:(columns // 4) * 3]
    cv2.imwrite("../test_cut.jpg", img_copy)


camera_intrinsics = {
    "fx": 1906.529041,
    "fy": 1906.529041,
    "cx": 1922.900124,
    "cy": 1076.546413,
    "k1": 1.131066,
    "k2": 0.226847,
    "p1": -1.2e-05,
    "p2": 3.2e-05,
    "k3": 0.003253,
    "k4": 1.496241,
    "k5": 0.542232,
    "k6": 0.031716,
}


def test_undistort():
    img = cv2.imread("../test.jpg")

    K = numpy.array([
        [camera_intrinsics["fx"], 0, camera_intrinsics["cx"]],
        [0, camera_intrinsics["fy"], camera_intrinsics["cy"]],
        [0, 0, 1]
    ], dtype=numpy.float64)
    D = numpy.array([
        camera_intrinsics["k1"],
        camera_intrinsics["k2"],
        camera_intrinsics["p1"],
        camera_intrinsics["p2"],
        camera_intrinsics["k3"],
        camera_intrinsics["k4"],
        camera_intrinsics["k5"],
        camera_intrinsics["k6"],
    ], dtype=numpy.float64)
    undistorted = cv2.undistort(img, K, D)
    cv2.imwrite("../test_undistorted.jpg", undistorted)
