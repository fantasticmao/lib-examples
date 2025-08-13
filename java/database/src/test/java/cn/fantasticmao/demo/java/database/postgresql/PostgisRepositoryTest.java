package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.Shop;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * PostgisRepositoryTest
 *
 * @author fantasticmao
 * @since 2025-08-13
 */
public class PostgisRepositoryTest {

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        try (PostgisRepository repository = new PostgisRepository()) {
            boolean insertStatus = repository.insert(Shop.MACDONALD_YU_GARDEN);
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(Shop.MACDONALD_YUE_HUI);
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(Shop.MACDONALD_XIN_MEI);
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(Shop.MACDONALD_HUAI_HAI);
            Assert.assertTrue(insertStatus);
            insertStatus = repository.insert(Shop.MACDONALD_MO_LING);
            Assert.assertTrue(insertStatus);

            List<Shop> shopList = repository.selectAll();
            Assert.assertEquals(5, shopList.size());

            List<List<String>> shopListMap = repository.selectAllAsGeoJson();
            Assert.assertEquals(5, shopListMap.size());
            for (List<String> row : shopListMap) {
                System.out.printf("shopName: %s, location_ewkt: '%s', location_geojson: '%s'\n",
                    row.get(0), row.get(1), row.get(2));
            }

            shopListMap = repository.selectByDistance(Shop.ORIENTAL_PEARL_TOWER.getLocation(), 2000);
            Assert.assertEquals(3, shopListMap.size());
            for (List<String> row : shopListMap) {
                System.out.printf("shopName: %s, location_text: '%s', distance: '%s'\n",
                    row.get(0), row.get(1), row.get(2));
            }
        }
    }
}
