import open3d as o3d


def test_point_cloud():
    pcd = o3d.t.io.read_point_cloud("../939452553.pcd")
    positions = pcd.point.positions.numpy()
    print(positions.shape)
    print(positions.dtype)

    intensity = pcd.point["intensity"].numpy()
    print(intensity.shape)
    print(intensity.dtype)
