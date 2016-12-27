/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.statisticsmgt.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.opencps.statisticsmgt.NoSuchGovagencyLevelException;
import org.opencps.statisticsmgt.model.GovagencyLevel;
import org.opencps.statisticsmgt.model.impl.GovagencyLevelImpl;
import org.opencps.statisticsmgt.model.impl.GovagencyLevelModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the govagency level service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungnt
 * @see GovagencyLevelPersistence
 * @see GovagencyLevelUtil
 * @generated
 */
public class GovagencyLevelPersistenceImpl extends BasePersistenceImpl<GovagencyLevel>
	implements GovagencyLevelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GovagencyLevelUtil} to access the govagency level persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GovagencyLevelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
			GovagencyLevelModelImpl.FINDER_CACHE_ENABLED,
			GovagencyLevelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
			GovagencyLevelModelImpl.FINDER_CACHE_ENABLED,
			GovagencyLevelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
			GovagencyLevelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GovagencyLevelPersistenceImpl() {
		setModelClass(GovagencyLevel.class);
	}

	/**
	 * Caches the govagency level in the entity cache if it is enabled.
	 *
	 * @param govagencyLevel the govagency level
	 */
	@Override
	public void cacheResult(GovagencyLevel govagencyLevel) {
		EntityCacheUtil.putResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
			GovagencyLevelImpl.class, govagencyLevel.getPrimaryKey(),
			govagencyLevel);

		govagencyLevel.resetOriginalValues();
	}

	/**
	 * Caches the govagency levels in the entity cache if it is enabled.
	 *
	 * @param govagencyLevels the govagency levels
	 */
	@Override
	public void cacheResult(List<GovagencyLevel> govagencyLevels) {
		for (GovagencyLevel govagencyLevel : govagencyLevels) {
			if (EntityCacheUtil.getResult(
						GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
						GovagencyLevelImpl.class, govagencyLevel.getPrimaryKey()) == null) {
				cacheResult(govagencyLevel);
			}
			else {
				govagencyLevel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all govagency levels.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(GovagencyLevelImpl.class.getName());
		}

		EntityCacheUtil.clearCache(GovagencyLevelImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the govagency level.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GovagencyLevel govagencyLevel) {
		EntityCacheUtil.removeResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
			GovagencyLevelImpl.class, govagencyLevel.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GovagencyLevel> govagencyLevels) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GovagencyLevel govagencyLevel : govagencyLevels) {
			EntityCacheUtil.removeResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
				GovagencyLevelImpl.class, govagencyLevel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new govagency level with the primary key. Does not add the govagency level to the database.
	 *
	 * @param govagencylevel the primary key for the new govagency level
	 * @return the new govagency level
	 */
	@Override
	public GovagencyLevel create(long govagencylevel) {
		GovagencyLevel govagencyLevel = new GovagencyLevelImpl();

		govagencyLevel.setNew(true);
		govagencyLevel.setPrimaryKey(govagencylevel);

		return govagencyLevel;
	}

	/**
	 * Removes the govagency level with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param govagencylevel the primary key of the govagency level
	 * @return the govagency level that was removed
	 * @throws org.opencps.statisticsmgt.NoSuchGovagencyLevelException if a govagency level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GovagencyLevel remove(long govagencylevel)
		throws NoSuchGovagencyLevelException, SystemException {
		return remove((Serializable)govagencylevel);
	}

	/**
	 * Removes the govagency level with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the govagency level
	 * @return the govagency level that was removed
	 * @throws org.opencps.statisticsmgt.NoSuchGovagencyLevelException if a govagency level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GovagencyLevel remove(Serializable primaryKey)
		throws NoSuchGovagencyLevelException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GovagencyLevel govagencyLevel = (GovagencyLevel)session.get(GovagencyLevelImpl.class,
					primaryKey);

			if (govagencyLevel == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGovagencyLevelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(govagencyLevel);
		}
		catch (NoSuchGovagencyLevelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GovagencyLevel removeImpl(GovagencyLevel govagencyLevel)
		throws SystemException {
		govagencyLevel = toUnwrappedModel(govagencyLevel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(govagencyLevel)) {
				govagencyLevel = (GovagencyLevel)session.get(GovagencyLevelImpl.class,
						govagencyLevel.getPrimaryKeyObj());
			}

			if (govagencyLevel != null) {
				session.delete(govagencyLevel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (govagencyLevel != null) {
			clearCache(govagencyLevel);
		}

		return govagencyLevel;
	}

	@Override
	public GovagencyLevel updateImpl(
		org.opencps.statisticsmgt.model.GovagencyLevel govagencyLevel)
		throws SystemException {
		govagencyLevel = toUnwrappedModel(govagencyLevel);

		boolean isNew = govagencyLevel.isNew();

		Session session = null;

		try {
			session = openSession();

			if (govagencyLevel.isNew()) {
				session.save(govagencyLevel);

				govagencyLevel.setNew(false);
			}
			else {
				session.merge(govagencyLevel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
			GovagencyLevelImpl.class, govagencyLevel.getPrimaryKey(),
			govagencyLevel);

		return govagencyLevel;
	}

	protected GovagencyLevel toUnwrappedModel(GovagencyLevel govagencyLevel) {
		if (govagencyLevel instanceof GovagencyLevelImpl) {
			return govagencyLevel;
		}

		GovagencyLevelImpl govagencyLevelImpl = new GovagencyLevelImpl();

		govagencyLevelImpl.setNew(govagencyLevel.isNew());
		govagencyLevelImpl.setPrimaryKey(govagencyLevel.getPrimaryKey());

		govagencyLevelImpl.setGovagencylevel(govagencyLevel.getGovagencylevel());
		govagencyLevelImpl.setCompanyId(govagencyLevel.getCompanyId());
		govagencyLevelImpl.setGroupId(govagencyLevel.getGroupId());
		govagencyLevelImpl.setUserId(govagencyLevel.getUserId());
		govagencyLevelImpl.setCreateDate(govagencyLevel.getCreateDate());
		govagencyLevelImpl.setModifiedDate(govagencyLevel.getModifiedDate());
		govagencyLevelImpl.setGovAgencyCode(govagencyLevel.getGovAgencyCode());
		govagencyLevelImpl.setAdministrationLevel(govagencyLevel.getAdministrationLevel());

		return govagencyLevelImpl;
	}

	/**
	 * Returns the govagency level with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the govagency level
	 * @return the govagency level
	 * @throws org.opencps.statisticsmgt.NoSuchGovagencyLevelException if a govagency level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GovagencyLevel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGovagencyLevelException, SystemException {
		GovagencyLevel govagencyLevel = fetchByPrimaryKey(primaryKey);

		if (govagencyLevel == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGovagencyLevelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return govagencyLevel;
	}

	/**
	 * Returns the govagency level with the primary key or throws a {@link org.opencps.statisticsmgt.NoSuchGovagencyLevelException} if it could not be found.
	 *
	 * @param govagencylevel the primary key of the govagency level
	 * @return the govagency level
	 * @throws org.opencps.statisticsmgt.NoSuchGovagencyLevelException if a govagency level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GovagencyLevel findByPrimaryKey(long govagencylevel)
		throws NoSuchGovagencyLevelException, SystemException {
		return findByPrimaryKey((Serializable)govagencylevel);
	}

	/**
	 * Returns the govagency level with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the govagency level
	 * @return the govagency level, or <code>null</code> if a govagency level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GovagencyLevel fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		GovagencyLevel govagencyLevel = (GovagencyLevel)EntityCacheUtil.getResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
				GovagencyLevelImpl.class, primaryKey);

		if (govagencyLevel == _nullGovagencyLevel) {
			return null;
		}

		if (govagencyLevel == null) {
			Session session = null;

			try {
				session = openSession();

				govagencyLevel = (GovagencyLevel)session.get(GovagencyLevelImpl.class,
						primaryKey);

				if (govagencyLevel != null) {
					cacheResult(govagencyLevel);
				}
				else {
					EntityCacheUtil.putResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
						GovagencyLevelImpl.class, primaryKey,
						_nullGovagencyLevel);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(GovagencyLevelModelImpl.ENTITY_CACHE_ENABLED,
					GovagencyLevelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return govagencyLevel;
	}

	/**
	 * Returns the govagency level with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param govagencylevel the primary key of the govagency level
	 * @return the govagency level, or <code>null</code> if a govagency level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public GovagencyLevel fetchByPrimaryKey(long govagencylevel)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)govagencylevel);
	}

	/**
	 * Returns all the govagency levels.
	 *
	 * @return the govagency levels
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<GovagencyLevel> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the govagency levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statisticsmgt.model.impl.GovagencyLevelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of govagency levels
	 * @param end the upper bound of the range of govagency levels (not inclusive)
	 * @return the range of govagency levels
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<GovagencyLevel> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the govagency levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statisticsmgt.model.impl.GovagencyLevelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of govagency levels
	 * @param end the upper bound of the range of govagency levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of govagency levels
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<GovagencyLevel> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<GovagencyLevel> list = (List<GovagencyLevel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GOVAGENCYLEVEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GOVAGENCYLEVEL;

				if (pagination) {
					sql = sql.concat(GovagencyLevelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GovagencyLevel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<GovagencyLevel>(list);
				}
				else {
					list = (List<GovagencyLevel>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the govagency levels from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (GovagencyLevel govagencyLevel : findAll()) {
			remove(govagencyLevel);
		}
	}

	/**
	 * Returns the number of govagency levels.
	 *
	 * @return the number of govagency levels
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GOVAGENCYLEVEL);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the govagency level persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.opencps.statisticsmgt.model.GovagencyLevel")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GovagencyLevel>> listenersList = new ArrayList<ModelListener<GovagencyLevel>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GovagencyLevel>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(GovagencyLevelImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_GOVAGENCYLEVEL = "SELECT govagencyLevel FROM GovagencyLevel govagencyLevel";
	private static final String _SQL_COUNT_GOVAGENCYLEVEL = "SELECT COUNT(govagencyLevel) FROM GovagencyLevel govagencyLevel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "govagencyLevel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GovagencyLevel exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GovagencyLevelPersistenceImpl.class);
	private static GovagencyLevel _nullGovagencyLevel = new GovagencyLevelImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<GovagencyLevel> toCacheModel() {
				return _nullGovagencyLevelCacheModel;
			}
		};

	private static CacheModel<GovagencyLevel> _nullGovagencyLevelCacheModel = new CacheModel<GovagencyLevel>() {
			@Override
			public GovagencyLevel toEntityModel() {
				return _nullGovagencyLevel;
			}
		};
}