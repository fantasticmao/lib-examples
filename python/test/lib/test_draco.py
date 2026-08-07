import DracoPy
import open3d as o3d

pcd_file = "../939452553.pcd"
drc_file = "../939452553.drc"


def test_draco_encode():
    pcd = o3d.t.io.read_point_cloud(pcd_file)
    pcd_positions = pcd.point.positions.numpy()
    print("pcd_positions: ")
    print(pcd_positions[:5])

    generic_attrs = {
        "intensity": pcd.point["intensity"].numpy(),
        "ring": pcd.point["ring"].numpy(),
        "sensor_id": pcd.point["sensor_id"].numpy(),
    }

    compressed_bytes = DracoPy.encode(
        pcd_positions,
        quantization_bits=14,  # 16
        compression_level=7,
        preserve_order=True,
        generic_attributes=generic_attrs,
    )

    with open(drc_file, "wb") as f:
        f.write(compressed_bytes)

    print(f"draco encode success, {drc_file} size: {len(compressed_bytes) / 1024 / 1024:.2f} MB")

    draco_cloud = DracoPy.decode(compressed_bytes)
    draco_point = draco_cloud.points
    print("draco_point: ")
    print(draco_point[:5])


def test_draco_decode():
    with open(drc_file, "rb") as f:
        compressed_bytes = f.read()

    draco_cloud = DracoPy.decode(compressed_bytes)

    pcd = o3d.t.geometry.PointCloud()
    pcd.point.positions = o3d.core.Tensor(draco_cloud.points, dtype=o3d.core.float32)
    pcd.point["intensity"] = o3d.core.Tensor(
        draco_cloud.get_attribute_by_name("intensity")["data"], dtype=o3d.core.uint8
    )
    pcd.point["ring"] = o3d.core.Tensor(
        draco_cloud.get_attribute_by_name("ring")["data"], dtype=o3d.core.uint16
    )
    pcd.point["sensor_id"] = o3d.core.Tensor(
        draco_cloud.get_attribute_by_name("sensor_id")["data"], dtype=o3d.core.uint8
    )

    o3d.visualization.draw_geometries(geometry_list=[pcd.to_legacy()], width=1024, height=768)
