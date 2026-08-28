import os

import open3d as o3d

current_dir = os.path.dirname(os.path.abspath(__file__))
pcd_file = f"{current_dir}/../939452553.pcd"


def test_point_cloud_read():
    pcd = o3d.t.io.read_point_cloud(pcd_file)
    positions = pcd.point.positions.numpy()
    print(positions.shape)
    print(positions.dtype)

    intensity = pcd.point["intensity"].numpy()
    print(intensity.shape)
    print(intensity.dtype)


def test_point_cloud_visualization():
    pcd = o3d.t.io.read_point_cloud(pcd_file)
    o3d.visualization.draw_geometries(geometry_list=[pcd.to_legacy()], width=1024, height=768)
